package com.buzzbike.bjss.qrandnfcdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
  private val PERMISSION_REQUEST: Int = 2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    naviagateToOutside(null)
    getDataFromTag(intent)
    getDataFromQr()
    requestCameraPermission()
  }

  fun getDataFromQr() {
    intent ?: return
    FirebaseDynamicLinks.getInstance()
        .getDynamicLink(intent)
        .addOnSuccessListener(this) { pendingDynamicLinkData ->
          pendingDynamicLinkData?.link?.let { uri ->
            navigation.selectedItemId = R.id.outside
            naviagateToOutside(uri)
          }
        }
  }

  private fun requestCameraPermission() {
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
          PERMISSION_REQUEST)
    }
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    getDataFromTag(intent)
  }

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.qrcode -> {
        navigateToQrCode()
        return@OnNavigationItemSelectedListener true
      }
      R.id.nfc -> {
        naviagateToNfc(null)
        return@OnNavigationItemSelectedListener true
      }
      R.id.outside -> {
        naviagateToOutside(null)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  private fun naviagateToOutside(uri: Uri?) {
    ExternalFragment.navigateTo(supportFragmentManager, R.id.frame, uri)
  }

  private fun naviagateToNfc(nfcText: String?) {
    NFCFragment.navigateTo(supportFragmentManager, R.id.frame, nfcText)
  }

  private fun navigateToQrCode() {
    QrFragment.navigateTo(supportFragmentManager, R.id.frame)
  }

  private fun getDataFromTag(intent: Intent?) {
    if (intent?.getAction()?.equals(NfcAdapter.ACTION_NDEF_DISCOVERED) == true) {
      navigation.selectedItemId = R.id.nfc
      val msgs: Array<Parcelable> = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
      if (msgs == null || msgs.isEmpty()) {
        return
      }
      val records = (msgs.first() as NdefMessage).records
      if (records == null || records.isEmpty()) {
        return
      }
      naviagateToNfc(Reader.readText(records[0]))
    }

  }

}
