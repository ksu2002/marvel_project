package com.example.marvelproject.data.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.R
import com.example.marvelproject.active
import com.google.firebase.analytics.FirebaseAnalytics


class CharactersListFragment : Fragment() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var mainAdapter: CharactersAdapter
    private lateinit var recyclerView: RecyclerView

    private val characterCLickListener by lazy {
        CharactersAdapter.CharacterCLickListener { id ->
            val fragment = CharacterFragment()
            val bundle = Bundle()
            bundle.putString(CharacterFragment.CHARACTER_ID, id)
            fragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.add(R.id.fragment_container_view, fragment, "fragmentCharacter")
                ?.hide(active)?.show(fragment)?.commit()
            active = fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters_list, container, false)
        view.apply {
            recyclerView = findViewById(R.id.recycler)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        mainAdapter = CharactersAdapter(arrayListOf(), activity?.supportFragmentManager)

        mainAdapter.characterCLickListener = characterCLickListener
        val numberOfColumns = 2

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, numberOfColumns)
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as GridLayoutManager).orientation
                )
            )
            adapter = mainAdapter
        }
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            characters ?: return@observe
            mainAdapter.addCharacters(characters.data.results)
        }
    }
}
