package com.example.xmlstandardmethod.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.databinding.FragmentDetailBinding
import com.example.xmlstandardmethod.repository.StandardRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment: Fragment(){

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = arguments?.getInt("itemId") ?: -1
        if (itemId != -1) viewModel.getDetailItem(itemId)

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.details.collect{resource ->
                    when(resource){
                        is Resource.Loading -> {
                            println("Loading Details...")
                        }
                        is Resource.Success ->{
                            resource.data?.let {detail->
                                binding!!.userIdView2.text = detail.userId.toString()
                                binding!!.titleView2.text = detail.title
                                binding!!.completedView2.text = detail.completed.toString()
                            }
                        }
                        is Resource.Error ->{
                            println("Error: ${resource.message}")
                        }
                    }

                }
            }
        }



    }


}