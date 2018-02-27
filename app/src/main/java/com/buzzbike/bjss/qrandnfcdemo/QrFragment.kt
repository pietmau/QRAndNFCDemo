package com.buzzbike.bjss.qrandnfcdemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrFragment : Fragment(), ZXingScannerView.ResultHandler {
  private var scannerView: ZXingScannerView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    scannerView = ZXingScannerView(activity as Context?)
    return scannerView
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

  public override fun onResume() {
    super.onResume()
    scannerView?.setResultHandler(this)
    scannerView?.startCamera()
  }

  public override fun onPause() {
    super.onPause()
    scannerView?.stopCamera()
  }

  override fun handleResult(rawResult: Result) {
    scannerView?.resumeCameraPreview(this)
  }
}
