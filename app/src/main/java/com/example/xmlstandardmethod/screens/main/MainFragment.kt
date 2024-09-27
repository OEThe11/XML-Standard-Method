package com.example.xmlstandardmethod.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlstandardmethod.R
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//@AndroidEntryPoint
//class MainFragment : Fragment(), MainAdapter.OnItemClickListener {
//
//    private val viewModel: MainViewModel by viewModels()
//
//    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//            _binding = FragmentMainBinding.inflate(inflater, container, false)
//            return  binding!!.root
//        }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        //Observe and handle the standard list data
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.standardList.collect{ resource ->
//                    when(resource){
//                        is Resource.Loading -> {
//                            // For Loading
//                            println("Loading standards...")
//                        }
//                        is Resource.Success -> {
//                            // Update UI with the list of standards
//                            resource.data?.let {standardList ->
//                                val adapter = MainAdapter(standardList, this@MainFragment)
//                                binding!!.recyclerView.adapter= adapter
//                            }
//                        }
//                        is Resource.Error -> {
//                            println("Error: ${resource.message}")
//                        }
//                    }
//                }
//        }
//    }
//
//        viewModel.getStandardList()
//
//
//    }

//    override fun onItemClick(id: Int) {
//        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(id)
//        findNavController().navigate(action)
//    }
//}