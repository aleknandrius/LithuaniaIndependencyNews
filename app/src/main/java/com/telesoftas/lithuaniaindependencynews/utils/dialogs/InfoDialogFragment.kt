package com.telesoftas.lithuaniaindependencynews.utils.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.SpannableString
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.telesoftas.lithuaniaindependencynews.R

class InfoDialogFragment : DialogFragment() {

    private var listener: InfoDialogClickListener? = null
    private var declineListener: InfoDialogClickListener? = null
    private var acceptButtonTitle = ""
    private var declineButtonTitle = ""
    private var spannableString: SpannableString? = null

    fun show(fragmentManager: FragmentManager, listener: InfoDialogClickListener?) {
        this.listener = listener
        show(fragmentManager)
    }

    fun show(fragmentManager: FragmentManager, listener: InfoDialogClickListener?,
             declineListener: InfoDialogClickListener?) {
        this.declineListener = declineListener
        show(fragmentManager, listener)
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG_INFO_DIALOG_FRAGMENT)
    }

    fun withAcceptButton(buttonTitle: String): InfoDialogFragment {
        acceptButtonTitle = buttonTitle
        return this
    }

    fun withDeclineButton(buttonTitle: String): InfoDialogFragment {
        declineButtonTitle = buttonTitle
        return this
    }

    fun withSpannableText(text: SpannableString): InfoDialogFragment {
        spannableString = text
        return this
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.fragment_info_dialog)
        setDialogText(dialog)
        setupButtons(dialog)
        isCancelable = false
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    private fun setDialogText(dialog: Dialog) {
        val descriptionTextView = dialog.findViewById<TextView>(R.id.infoDialogDescTextView)
        val titleTextView = dialog.findViewById<TextView>(R.id.infoDialogTitleTextView)

        val title = arguments!!.getString(KEY_TITLE_ID)
        if (title.isEmpty()) {
            titleTextView.visibility = View.GONE
        } else {
            titleTextView.text = title
        }

        descriptionTextView.text = arguments!!.getString(KEY_DESC_ID)
        if (spannableString != null) {
            descriptionTextView.text = spannableString
        }
    }

    private fun setupButtons(dialog: Dialog) {
        val acceptButton = dialog.findViewById<Button>(R.id.infoDialogAcceptButton)
        val declineButton = dialog.findViewById<Button>(R.id.infoDialogDeclineButton)

        acceptButton.setOnClickListener {
            listener?.onDialogButtonClicked()
            dismiss()
        }
        declineButton.setOnClickListener {
            dismiss()
            declineListener?.onDialogButtonClicked()
        }

        if (acceptButtonTitle.isNotEmpty()) {
            acceptButton.text = acceptButtonTitle
            if (declineButtonTitle.isNotEmpty()) {
                declineButton.text = declineButtonTitle
            } else {
                declineButton.visibility = View.GONE
            }
        } else {
            if (declineButtonTitle.isNotEmpty()) {
                acceptButton.visibility = View.GONE
                declineButton.text = declineButtonTitle
            }
        }

    }

    companion object {
        private const val TAG_INFO_DIALOG_FRAGMENT = "tag.info_dialog_fragment"
        private const val KEY_TITLE_ID = "key.dialog_title_id"
        private const val KEY_DESC_ID = "key.dialog_desc_id"

        fun newInstance(title: String, description: String): InfoDialogFragment {
            val bundle = Bundle()
            bundle.putString(KEY_TITLE_ID, title)
            bundle.putString(KEY_DESC_ID, description)
            val fragment = InfoDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    interface InfoDialogClickListener {
        fun onDialogButtonClicked()
    }
}