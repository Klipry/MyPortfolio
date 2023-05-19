package com.example.dkakfpkoaopf.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dkakfpkoaopf.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private val layoutList: MutableList<LinearLayout> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Add the layouts you want to apply animation to the mutable list
        layoutList.add(binding.WorkExperience)
        layoutList.add(binding.InfoMy)
        layoutList.add(binding.OneOrder)
        layoutList.add(binding.TwoOrder)
        layoutList.add(binding.ThreeOrder)
        layoutList.add(binding.FourOrder)

        // Initialize animation for each layout
        layoutList.forEach { layout ->
            homeViewModel.initialize(layout)
        }

        val scrollView = binding.scrollView4
        scrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollViewHeight = scrollView.height
            val visibleRect = Rect(0, scrollView.scrollY, scrollView.width, scrollView.scrollY + scrollViewHeight)

            layoutList.forEach { layout ->
                val containerRect = Rect()
                layout.getGlobalVisibleRect(containerRect)

                if (Rect.intersects(visibleRect, containerRect)) {
                    homeViewModel.startAnimation(layout)
                }
            }
        }


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
