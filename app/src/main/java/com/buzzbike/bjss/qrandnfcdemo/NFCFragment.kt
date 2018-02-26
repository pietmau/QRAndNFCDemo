package com.buzzbike.bjss.qrandnfcdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_nfc.*

class NFCFragment : Fragment() {
  private var tagText: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      tagText = arguments.getString(NFC_TAG)
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    label.setText(tagText)
    return inflater!!.inflate(R.layout.fragment_nfc, container, false)
  }

  companion object {
    private val TAG: String? = NFCFragment::class.simpleName
    private val NFC_TAG = "tagTexy"

    private fun newInstance(tagText: String?): NFCFragment {
      val fragment = NFCFragment()
      val args = Bundle()
      args.putString(NFC_TAG, tagText)
      fragment.arguments = args
      return fragment
    }

    fun navigateTo(fragmentManager: FragmentManager, container: Int, tagText: String?) {
      val frag = fragmentManager.findFragmentByTag(TAG) ?: newInstance(tagText)
      if (frag.isAdded) {
        return
      }
      fragmentManager.beginTransaction().replace(container, frag, TAG)
    }
  }
}
