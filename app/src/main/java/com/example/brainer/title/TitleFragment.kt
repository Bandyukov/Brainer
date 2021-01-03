package com.example.brainer.title

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.brainer.R
import com.example.brainer.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private lateinit var viewModel: TitleViewModel
    private lateinit var buttons: Array<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title, container, false
        )

        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        buttons =
            arrayOf(binding.buttonEasyLevel, binding.buttonMiddleLevel, binding.buttonHardLevel)

        for (now: Button in buttons) {
            now.setOnClickListener() {
                val btn: Button = it as Button
                val mod: Int

                when (btn.id) {
                    buttons[0].id -> mod = 1
                    buttons[1].id -> mod = 2
                    else -> mod = 3
                }

                val bundle = Bundle()
                bundle.putInt("mod", mod)

                findNavController().navigate(R.id.action_titleFragment_to_gameFragment, bundle)

            }
        }

        binding.imageButton.setOnClickListener{
            it.findNavController().navigate(R.id.action_titleFragment_to_recordsFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.info -> findNavController().navigate(R.id.action_titleFragment_to_aboutGameFragment)
            R.id.settings -> findNavController().navigate(R.id.action_titleFragment_to_settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }


}