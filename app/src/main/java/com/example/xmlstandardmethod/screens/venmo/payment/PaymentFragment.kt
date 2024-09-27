package com.example.xmlstandardmethod.screens.venmo.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.xmlstandardmethod.data.Resource
import com.example.xmlstandardmethod.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val amount = 50.00  // Mock payment amount

        binding.btnPayWithVenmo.setOnClickListener {
            // Trigger the payment when the button is clicked
            viewModel.initiateVenmoPayment(amount)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.paymentResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvResult.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvResult.visibility = View.VISIBLE
                    binding.tvResult.text = "Payment Successful: ${result.data}"
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvResult.visibility = View.VISIBLE
                    binding.tvResult.text = "Payment Failed: ${result.message}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}