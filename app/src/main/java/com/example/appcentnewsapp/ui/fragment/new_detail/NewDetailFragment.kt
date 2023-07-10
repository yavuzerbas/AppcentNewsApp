package com.example.appcentnewsapp.ui.fragment.new_detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appcentnewsapp.R
import com.example.appcentnewsapp.data.utils.Util
import com.example.appcentnewsapp.databinding.FragmentNewDetailBinding
import com.squareup.picasso.Picasso

class NewDetailFragment : Fragment() {
    private var binding : FragmentNewDetailBinding? = null

    private val args: NewDetailFragmentArgs by navArgs()

    private var imageUrl : String? = null
    private var title: String? = null
    private var author: String? = null
    private var date: String? = null
    private var description : String? = null
    private var newUrl : String? = null
    private var isFavorite: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewDetailBinding.inflate(inflater, container, false)

        imageUrl = args.imageUrl
        title = args.title
        author = args.author
        date = Util.parseDate(args.date)
        description = args.description
        newUrl = args.newUrl
        isFavorite = args.isFavorite

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUI()
        btnBackOnClickListener()
        btnNewsSourceOnClickListener()
        btnShareOnClickListener()
        btnFavoriteOnClickListener()
    }

    private fun prepareUI() {
        Picasso
            .get()
            .load(imageUrl ?: "https://play-lh.googleusercontent.com/8LYEbSl48gJoUVGDUyqO5A0xKlcbm2b39S32xvm_h-8BueclJnZlspfkZmrXNFX2XQ")
            .into(binding?.ivNewDetail)
        binding?.tvNewTitleNewDetailFragment?.text = title ?: ""
        binding?.tvAuthor?.text = author ?: ""
        binding?.tvDate?.text = date ?: ""
        binding?.tvNewFullDescription?.text = description ?: ""
        prepareFavoriteButton()
    }
    private fun prepareFavoriteButton(){
        if(isFavorite == null || !isFavorite!!){
            // Change Icon to baseline_favorite_border_24
            binding?.btnIsFavorite?.setImageResource(R.drawable.baseline_favorite_border_24)
            // Change Icon color to black
            binding?.btnIsFavorite?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black))
        }
        else{
            // Change Icon to baseline_favorite_24
            binding?.btnIsFavorite?.setImageResource(R.drawable.baseline_favorite_24)
            // Change Icon color to Red
            binding?.btnIsFavorite?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
        }
    }

    private fun btnBackOnClickListener(){
        binding?.btnBackFragmentNewDetail?.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun btnNewsSourceOnClickListener(){
        binding?.btnNewsSource?.setOnClickListener {
            findNavController().navigate(NewDetailFragmentDirections.
                actionNewDetailFragmentToNewWebViewFragment(
                    newUrl
            ))
        }
    }
    private fun btnShareOnClickListener(){
        binding?.btnShare?.setOnClickListener {
            val sendIntent: Intent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,newUrl)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivity(shareIntent)
        }
    }
    private fun btnFavoriteOnClickListener(){
        binding?.btnIsFavorite?.setOnClickListener {
            toggleFavoriteButton()
        }
    }
    private fun toggleFavoriteButton(){
        isFavorite = isFavorite== null || !isFavorite!!
        prepareFavoriteButton()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}