package com.example.memestream1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatFragment : Fragment() {
//
//    private lateinit var catImageView: ImageView
//    private lateinit var getCatButton: Button
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view = inflater.inflate(R.layout.fragment_cat, container, false)
//        catImageView = view.findViewById(R.id.catImageView)
//        getCatButton = view.findViewById(R.id.btnGetCat)
//
//        getCatButton.setOnClickListener {
//            fetchCatImage()
//        }
//
//        return view
//    }

//    private fun fetchCatImage() {
//        lifecycleScope.launch {
//            try {
//                val response = RetrofitInstance.api.getRandomCat()
//                val imageUrl = response.firstOrNull()?.url
//                if (imageUrl != null) {
//                    Glide.with(requireContext()).load(imageUrl).into(catImageView)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Toast.makeText(requireContext(), "Error fetching cat 😿", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
