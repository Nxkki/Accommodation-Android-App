package org.wit.accommodation.helpers


import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import org.wit.accommodation.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_accommodation_image.toString())
    intentLauncher.launch(chooseFile)
}

fun showImagePicker(parent: Activity, id: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_OPEN_DOCUMENT
    intent.addCategory(Intent.CATEGORY_OPENABLE)

    val chooseFile = Intent.createChooser(intent,R.string.select_accommodation_image.toString())
    parent.startActivityForResult(chooseFile, id)
}