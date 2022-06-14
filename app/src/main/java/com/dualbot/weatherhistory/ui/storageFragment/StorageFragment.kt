package com.dualbot.weatherhistory.ui.storageFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dualbot.weatherhistory.R
import com.dualbot.weatherhistory.domain.model.ExpandableTreeWeatherData
import com.dualbot.weatherhistory.domain.utils.Status
import com.dualbot.weatherhistory.ui.ViewModelFactory

class StorageFragment : Fragment(), AdapterCallback {

    private lateinit var viewModel: StorageViewModel
    private val adapter = RecyclerAdapter(this)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.storage_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.storage_recycler)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        setupViewModel()
        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        println("StorageFragment onCreateOptionsMenu")
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory.StorageViewModelFactory())[StorageViewModel::class.java]
    }

    private fun setupObserver() {
        viewModel.weatherList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    changeListData(it.data!!, adapter.list)
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        }
    }

    private fun changeListData(newList: List<ExpandableTreeWeatherData>,
                               oldList: List<ExpandableTreeWeatherData>) {
        val storageDiffUtil = StorageDiffUtil(newList, oldList)
        val diffResult = DiffUtil.calculateDiff(storageDiffUtil, true)
        adapter.setData(newList)
        diffResult.dispatchUpdatesTo(adapter)
    }

    override fun listChanged(
        newList: List<ExpandableTreeWeatherData>,
        oldList: List<ExpandableTreeWeatherData>
    ) {
        changeListData(newList, oldList)
    }
}