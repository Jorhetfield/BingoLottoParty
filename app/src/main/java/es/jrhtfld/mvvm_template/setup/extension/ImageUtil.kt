package es.jrhtfld.mvvm_template.setup.extension
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*


object ImageUtil {

    const val GALLERY_CODE = 1000
    const val CAMERA_CODE = 1001
    const val PERMISSION_CODE_GALLERY = 1002
    const val PERMISSION_CODE_CAMERA = 1003
    private const val TEMP_IMAGE_NAME = "elsha_temp"

    fun getBitmapFromUri(context: Context?, uri: Uri?): Bitmap? {
        val contentResolver = context?.contentResolver
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = uri?.let { contentResolver?.let { it1 -> ImageDecoder.createSource(it1, it) } }
            source?.let { ImageDecoder.decodeBitmap(it) }
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        }
    }

    fun createMultipart(context: Context?, bitmap: Bitmap): MultipartBody.Part {
        val imageFile = getFileFromBitmap(context, "file", bitmap)
        val requestBodyFromFile: RequestBody = imageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", "file", requestBodyFromFile)
    }

     fun getFileFromBitmap(context: Context?, fileName: String, bitmap: Bitmap, qualityJpeg: Int = 30): File {
        //Create a file to write bitmap data
        val file = File(context?.cacheDir, fileName)
        file.createNewFile()

        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, qualityJpeg, bos)
        val bitMapData = bos.toByteArray()

        //write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    fun correctOrientation(bitmap: Bitmap): Bitmap {
        val orientation = Exif.getOrientation(Exif.convertBitmapToByteArray(bitmap))
        logD("correctOrientation $orientation")
        return if (orientation == 0) bitmap else rotateBitmap(bitmap, orientation)
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.setRotate(degrees.toFloat())
        val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        bitmap.recycle()
        return rotated
    }

    fun generatePictureUri(context: Context?): Uri? {
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(TEMP_IMAGE_NAME, ".jpg", storageDir)
        file.let {
            return context?.let { it1 -> FileProvider.getUriForFile(it1, "${context.packageName}.fileprovider", it) }
        }
    }
}