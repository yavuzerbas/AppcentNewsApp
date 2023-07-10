package com.example.appcentnewsapp.ui.fragment.new_web_view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appcentnewsapp.databinding.FragmentNewWebViewBinding

class NewWebViewFragment : Fragment() {

    private var binding : FragmentNewWebViewBinding? = null

    private var webUrl: String? = null

    private val args : NewWebViewFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewWebViewBinding.inflate(inflater,container,false)
        webUrl = args.webUrl
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
        btnBackOnClickListener()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /**
     * 99% chatGPT
     * todo fix codes underlined red
     */
    private fun prepareUI() {
        val webView = binding?.wv
        val progressBar = binding?.progress

        webView?.settings?.javaScriptEnabled = true

        // Enabling built-in zoom
        webView?.settings?.builtInZoomControls = true
        webView?.settings?.displayZoomControls = false

        // Loading the URL
        webView?.loadUrl(webUrl ?: "")

        // Set WebViewClient to enable loading inside WebView
        webView?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // When the page loading is started, make the ProgressBar visible

                progressBar?.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                // When the page loading is done, hide the ProgressBar
                webView?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }

        // If you also want to load redirects inside the WebView, set a WebChromeClient like this:
        webView?.webChromeClient = WebChromeClient()
    }


    private fun btnBackOnClickListener(){
        binding?.btnBackFragmentWebView?.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}