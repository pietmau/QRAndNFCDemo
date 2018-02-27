package com.buzzbike.bjss.qrandnfcdemo

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrFragment : Fragment(), ZXingScannerView.ResultHandler {
  private var scannerView: ZXingScannerView? = null
  private var label: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_qr, container, false)
    scannerView = view.findViewById<ZXingScannerView>(R.id.scanner)
    label = view.findViewById<TextView>(R.id.text)
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
    label?.text = rawResult.text
    val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    tone.startTone(ToneGenerator.TONE_CDMA_PIP, 500)
    scannerView?.resumeCameraPreview(this)
  }
}
