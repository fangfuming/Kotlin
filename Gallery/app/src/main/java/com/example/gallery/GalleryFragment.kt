package com.example.gallery


import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.viewModel.GalleyViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel:GalleyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val galleryAdapter:GalleryAdapter = GalleryAdapter()
        recycleView.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }

        galleryViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleyViewModel::class.java)
        galleryViewModel.photoListLive.observe(this, Observer {
            galleryAdapter.submitList(it)
            swipeLayout.isRefreshing = false
        })

        galleryViewModel.photoListLive.value?:galleryViewModel.fetchData()

        swipeLayout.setOnRefreshListener {
            galleryViewModel.fetchData()
        }
    }

    //加载menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.swipe ->{
                swipeLayout.isRefreshing = true
                Handler().postDelayed(Runnable {galleryViewModel.fetchData()},1000)

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
