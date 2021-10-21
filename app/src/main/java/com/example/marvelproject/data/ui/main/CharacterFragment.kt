package com.example.marvelproject.data.ui.main

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.model.CharacterId
import com.example.marvelproject.data.model.CharacterInfo
import com.example.marvelproject.data.repository.Character
import com.example.marvelproject.data.repository.CharacterList

class CharacterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)
        view.apply {
            recyclerView = findViewById(R.id.recycler_comics)
        }

        return view
    }

    private val characterId by lazy { arguments?.getString(CHARACTER_ID) }
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(
            CharacterViewModel::class.java
        )
    }
    private lateinit var comicsAdapter: ComicsAdapter
    private lateinit var recyclerView: RecyclerView
    private var character: CharacterInfo? = null
    private val characterObserver = Observer<CharacterId?> {
        it ?: return@Observer

        character = it.data.results.firstOrNull()
        comicsAdapter.addComics(character?.comics?.items)
        requireView().findViewById<TextView>(R.id.character_name).text = character?.name
        requireView().findViewById<TextView>(R.id.character_description).text =
            character?.description
        val imageViewAvatar = requireView().findViewById<ImageView>(R.id.characterAvatar)
        val url = character?.image?.path + "." + character?.image?.extension
        Glide.with(imageViewAvatar.context)
            .load(url)
            .into(imageViewAvatar)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterId?.let { viewModel.loadCharacterInfo(it) }
        viewModel.character.observe(viewLifecycleOwner, characterObserver)
        comicsAdapter = ComicsAdapter(arrayListOf(), activity?.supportFragmentManager)
        val button = view.findViewById<Button>(R.id.open_comics)
        val buttonFav = view.findViewById<ImageButton>(R.id.add_in_fav)
        buttonFav?.setOnClickListener() {
            val text = "The hero has been added to the \"Favorites\" section!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
            if (character != null) {
                val characterFavorite = Character(
                    id = character!!.id,
                    name = character!!.name,
                    image = character!!.image
                )
                viewModel.addFavorite(characterFavorite)
            }
        }
        button?.setOnClickListener() {

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                    )
                )
                adapter = comicsAdapter
            }
        }
    }

    companion object {
        const val CHARACTER_ID = "characterId"
    }
}