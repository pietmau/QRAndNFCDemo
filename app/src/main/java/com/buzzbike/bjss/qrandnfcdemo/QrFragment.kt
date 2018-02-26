package com.buzzbike.bjss.qrandnfcdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class QrFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_qr, container, false)
    return view
  }

  companion object {
    private val TAG = QrFragment::class.simpleName

    private fun newInstance(): QrFragment {
      val fragment = QrFragment()
      return fragment
    }

    fun navigateTo(fragmentManager: FragmentManager, container: Int) {
      val frag = (fragmentManager.findFragmentByTag(QrFragment.TAG) ?: QrFragment.newInstance())
      if (frag.isAdded) {
        return
      }
      fragmentManager.beginTransaction().replace(container, frag, QrFragment.TAG).commit()
    }
  }
}
