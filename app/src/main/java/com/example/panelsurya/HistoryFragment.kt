package com.example.panelsurya

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var dbRef : DatabaseReference
    private lateinit var sapiRecyclerView: RecyclerView
    private lateinit var sapiArray: ArrayList<solar>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    companion object{
        fun newInstance():HistoryFragment{
            val fragment = HistoryFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment

        }

        fun newInstanse(): Fragment {
            val fragment = HistoryFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sapiRecyclerView = view.findViewById(R.id.historyPanel)
        sapiRecyclerView.layoutManager = LinearLayoutManager(context)
        sapiRecyclerView.setHasFixedSize(true)

        sapiArray = arrayListOf<solar>()
        getData()
    }

    private fun getData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Alvin_Alviano/history")
        dbRef.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (sapiSnapshot in snapshot.children){
                        val lokasiSapi  = sapiSnapshot.getValue(solar::class.java)
                        sapiArray.add(lokasiSapi!!)
                    }
                    var adapter = MyAdapter(sapiArray)
                    sapiRecyclerView.adapter = adapter

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "failed to get data", Toast.LENGTH_SHORT).show()
            }
        }

        )
    }

//    private var mListener: OnFragmentInteractionListener? =
//        null

//    fun Tab1() {
//        // Required empty public constructor
//    }

//    fun newInstance(
//        param1: String?,
//        param2: String?
//    ): HomeFragment? {
//        val fragment: HomeFragment = HomeFragment()
//        val args = Bundle()
//        args.putString(ARG_PARAM1, param1)
//        args.putString(ARG_PARAM2, param2)
//        fragment.setArguments(args)
//        return fragment
//    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//            mParam1 =
//                requireArguments().getString(ARG_PARAM1)
//            mParam2 =
//                requireArguments().getString(ARG_PARAM2)
//        }
//    }
//    fun onButtonPressed(uri: Uri?) {
//        mListener?.onFragmentInteraction(uri)
//    }
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mListener =
//            if (context is OnFragmentInteractionListener) {
//                context as OnFragmentInteractionListener
//            } else {
//                throw RuntimeException(
//                    context.toString()
//                            + " must implement OnFragmentInteractionListener"
//                )
//            }
//    }

//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
//    }



//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri?)
//    }


}