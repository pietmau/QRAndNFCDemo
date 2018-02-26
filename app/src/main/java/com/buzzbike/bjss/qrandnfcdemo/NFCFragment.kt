package com.buzzbike.bjss.qrandnfcdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NFCFragment : Fragment() {
  private var tagText: String? = null
  private var label: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      tagText = arguments.getString(NFC_TAG)
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_nfc, container, false)
    label = view.findViewById(R.id.label)
    if (tagText != null) {
      setText(tagText)
    }
    return view
  }

  fun setText(tagText1: String?) {
    label?.setText(tagText1)
  }

  companion object {
    private val TAG: String? = NFCFragment::class.simpleName
    private val NFC_TAG = "tagTexy"

    private fun newInstance(tagText: String?): NFCFragment {
      val fragment = NFCFragment()
      setArguments(tagText, fragment)
      return fragment
    }

    private fun setArguments(tagText: String?, fragment: NFCFragment) {
      val args = Bundle()
      args.putString(NFC_TAG, tagText)
      fragment.arguments = args
    }

    fun navigateTo(fragmentManager: FragmentManager, container: Int, tagText: String?) {
      val frag = (fragmentManager.findFragmentByTag(TAG) ?: newInstance(tagText)) as NFCFragment
      if (frag.isAdded) {
        setTag(tagText, frag)
        return
      }
      fragmentManager.beginTransaction().replace(container, frag, TAG).commit()
    }

    private fun setTag(tagText: String?, frag: NFCFragment) {
      if (tagText != null) {
        frag.setText(tagText)
        setArguments(tagText, frag)
      }
    }
  }
}
