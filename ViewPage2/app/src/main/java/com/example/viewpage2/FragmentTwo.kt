package com.example.viewpage2


import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_two.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView_scale.setOnClickListener{
            val factor = if (Random.nextBoolean()) {
                0.1f
            } else {
                -0.1f
            }
            ObjectAnimator.ofFloat(it,"scaleX",it.scaleX + factor).start()
            ObjectAnimator.ofFloat(it,"scaleY",it.scaleY + factor).start()
        }
    }

}
