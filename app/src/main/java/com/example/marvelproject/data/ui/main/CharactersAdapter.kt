package com.example.marvelproject.data.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.repository.Character

class CharactersAdapter(
    private val characters: ArrayList<Character>,
    private val fragmentManager: FragmentManager?
) :
    RecyclerView.Adapter<CharactersAdapter.DataViewHolder>() {

    var characterCLickListener: CharacterCLickListener? = null

    inner class DataViewHolder(itemView: View, private val fragmentManager: FragmentManager?) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {
            itemView.apply {
                val textCharacterName = this.findViewById<TextView>(R.id.textCharacterName)
                val imageCharacter = this.findViewById<ImageView>(R.id.imageCharacter)
                val container = this.findViewById<ConstraintLayout>(R.id.container)
                textCharacterName.text = character.name
                val url = character.image?.path + "." + character.image?.extension
                Glide.with(imageCharacter.context)
                    .load(url)
                    .into(imageCharacter)
                imageCharacter.setColorFilter(Color.DKGRAY, PorterDuff.Mode.LIGHTEN)
                container.setOnClickListener {
                    characterCLickListener?.onClick(character.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character, parent, false),
            fragmentManager
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(characters[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addCharacters(characters: List<Character>) {
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    fun interface CharacterCLickListener {
        fun onClick(id: String?)
    }
}