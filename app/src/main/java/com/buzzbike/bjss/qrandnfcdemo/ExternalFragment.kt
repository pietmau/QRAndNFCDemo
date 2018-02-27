package com.buzzbike.bjss.qrandnfcdemo

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ExternalFragment : Fragment() {
  private var uri: Uri? = null
  private var label: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    uri = arguments?.getParcelable<Uri>(NFC_TAG)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_nfc, container, false)
    label = view.findViewById(R.id.label)
    if (uri != null) {
      setText(uri)
    }
    return view
  }

  fun setText(uri: Uri?) {
    label?.setText(uri?.lastPathSegment)
  }

  companion object {
    private val TAG: String? = ExternalFragment::class.simpleName
    private val NFC_TAG = "tagTexy"

    private fun newInstance(tagText: Uri?): ExternalFragment {
      val fragment = ExternalFragment()
      setArguments(tagText, fragment)
      return fragment
    }

    private fun setArguments(uri: Uri?, fragment: ExternalFragment) {
      val args = Bundle()
      args.putParcelable(NFC_TAG, uri)
      fragment.arguments = args
    }

    fun navigateTo(fragmentManager: FragmentManager, container: Int, uri: Uri?) {
      val frag = (fragmentManager.findFragmentByTag(TAG) ?: newInstance(uri)) as ExternalFragment
      if (frag.isAdded) {
        setUri(uri, frag)
        return
      }
      fragmentManager.beginTransaction().replace(container, frag, TAG).commit()
    }

    private fun setUri(uri: Uri?, frag: ExternalFragment) {
      if (uri != null) {
        frag.setText(uri)
        setArguments(uri, frag)
      }
    }
  }
}