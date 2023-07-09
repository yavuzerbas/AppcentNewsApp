package com.example.appcentnewsapp.ui.fragment.new_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUI()
        btnBackOnClickListener()
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
    }
    private fun btnBackOnClickListener(){
        binding?.btnBackFragmentNewDetail?.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}