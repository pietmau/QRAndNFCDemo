package com.buzzbike.bjss.qrandnfcdemo;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

  companion object {
    const val QR_CODE_EXTRA_KEY: String = "code"
  }

  private var mScannerView: ZXingScannerView? = null

  public override fun onCreate(state: Bundle?) {
    super.onCreate(state)
    mScannerView = ZXingScannerView(this)
    setContentView(mScannerView)

  }

  public override fun onResume() {
    super.onResume()
    mScannerView?.setResultHandler(this)
    mScannerView?.startCamera()

  }

  public override fun onPause() {
    super.onPause()
    mScannerView?.stopCamera()

  }

  override fun handleResult(rawResult: Result) {
    mScannerView?.resumeCameraPreview(this)

  }

}