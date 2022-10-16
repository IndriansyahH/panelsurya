package com.example.panelsurya

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val ARG_PARAM1: String? = "param1"
    private val ARG_PARAM2 = "param2"

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mDatabaseRef : DatabaseReference? = null
    private var mFirebaseInstanse : FirebaseDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    companion object{
        fun newInstance():HomeFragment{
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFirebaseInstanse = FirebaseDatabase.getInstance()
        mDatabaseRef = mFirebaseInstanse!!.getReference("Juara").child("realtime")

        mDatabaseRef!!.child("pos").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvTiltval)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("vin").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvVoltval)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("nilairain").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvRainSensorVal)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("ldrb").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvLdrBelakangVal)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("ldrd").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvLdrDepanVal)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )
        mDatabaseRef!!.child("vin").addValueEventListener(
            object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    val txtPos = view.findViewById<TextView>(R.id.tvVoltval)
                    txtPos.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Failed To Get Data", Toast.LENGTH_LONG).show()
                }
            }
        )

        val btnLampu1 = view.findViewById<Button>(R.id.btnLampu)
        val btnLampu2 = view.findViewById<Button>(R.id.btnLampu2)
        val txtLampu1 = view.findViewById<TextView>(R.id.mTitlestat)
        val txtLampu2 = view.findViewById<TextView>(R.id.mTitlestat2)

        mFirebaseInstanse!!.getReference("Juara").child("lampu1").addValueEventListener(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    if (value == "1"){
                        btnLampu1.text = "on"
                        txtLampu1.text = "Lampu Keadaan Menyala"
                        btnLampu1.setBackgroundResource(R.drawable.rounded_btn2)
                        btnLampu1.setOnClickListener {
                            mFirebaseInstanse!!.getReference("Juara").child("lampu1").setValue("0")
                        }
                    }
                    if (value == "0"){
                        btnLampu1.text = "off"
//                        btnLampu1.background = resources.getDrawable(R.drawable.rounded_btn)
                        txtLampu1.text = "Lampu Keadaan Mati"
                        btnLampu1.setBackgroundResource(R.drawable.rounded_btn)
                        btnLampu1.setOnClickListener {
                            mFirebaseInstanse!!.getReference("Juara").child("lampu1").setValue("1")
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"failed get data", Toast.LENGTH_SHORT).show()
                }

            }
        )

        mFirebaseInstanse!!.getReference("Juara").child("lampu2").addValueEventListener(
            object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    if (value == "1"){
                        btnLampu2.text = "on"
                        txtLampu2.text = "Lampu Keadaan Menyala"
                        btnLampu2.setBackgroundResource(R.drawable.rounded_btn2)
//                        btnLampu2.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_btn2)
                        btnLampu2.setOnClickListener {
                            mFirebaseInstanse!!.getReference("Juara").child("lampu2").setValue("0")
                        }
                    }
                    if (value == "0"){
                        btnLampu2.text = "off"
                        txtLampu2.text = "Lampu Keadaan Mati"
                        btnLampu2.setBackgroundResource(R.drawable.rounded_btn)
//                        btnLampu2.background = ContextCompat.getDrawable(requireContext(), R.drawable.rounded_btn)
                        btnLampu2.setOnClickListener {
                            mFirebaseInstanse!!.getReference("Juara").child("lampu2").setValue("1")
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context,"failed get data", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

//    private var mListener: OnFragmentInteractionListener? = null

//    fun Tab1() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
//    fun newInstance(
//        param1: String?,
//        param2: String?
//    ): HomeFragment? {
//        val fragment: HomeFragment =
//            HomeFragment()
//        val args = Bundle()
//        args.putString(ARG_PARAM1, param1)
//        args.putString(ARG_PARAM2, param2)
//        fragment.setArguments(args)
//        return fragment
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//            mParam1 = requireArguments().getString(ARG_PARAM1)
//            mParam2 = requireArguments().getString(ARG_PARAM2)
//        }
//    }
//
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri?) {
//        if (mListener != null) {
//            mListener!!.onFragmentInteraction(uri)
//        }
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mListener = if (context is OnFragmentInteractionListener) {
//            context
//        } else {
//            throw RuntimeException(
//                context.toString()
//                        + " must implement OnFragmentInteractionListener"
//            )
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mListener = null
//    }
//
//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri?)
//    }
}