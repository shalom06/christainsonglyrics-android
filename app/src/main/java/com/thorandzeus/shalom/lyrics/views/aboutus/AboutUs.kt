package com.thorandzeus.shalom.lyrics.views.aboutus


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thorandzeus.shalom.lyrics.R
import mehdi.sakout.aboutpage.AboutPage


class AboutUs : Fragment() {

    companion object {
        fun newInstance() = AboutUs()
    }

    private lateinit var viewModel: AboutUsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return AboutPage(context)
                .isRTL(false)
                .setImage(R.drawable.icon)
                .setDescription("This is a project created with the goal of helping the church I went to." +
                        "I am a full time student and work part time so may be  a little busy to update . " +
                        "God Bless! ")
                .addEmail("lightingcoders@gmail.com","Contact me")
                .addPlayStore("com.thorandzeus.shalom.lyrics")
                .addGitHub("shalom06", "Find me on github!")
                .addInstagram("shalom_mathews", "Shalom Mathews")
                .addTwitter("shalom_mathews")
                .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutUsViewModel::class.java)

    }


}
