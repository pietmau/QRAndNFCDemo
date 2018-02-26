package com.buzzbike.bjss.qrandnfcdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class QrFragment : Fragment() {
  private var mParam1: String? = null
  private var mParam2: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      mParam1 = arguments.getString(ARG_PARAM1)
      mParam2 = arguments.getString(ARG_PARAM2)
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_qr, container, false)
    return view
  }

  companion object {
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    fun newInstance(param1: String, param2: String): QrFragment {
      val fragment = QrFragment()
      val args = Bundle()
      args.putString(ARG_PARAM1, param1)
      args.putString(ARG_PARAM2, param2)
      fragment.arguments = args
      return fragment
    }
  }
}// Required empty public constructor
