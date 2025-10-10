package com.moez.QKSMS.feature.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.octoshrimpy.quik.R

class MenuBottomSheet(
    private val onMenuSelected: (MenuOption) -> Unit
) : BottomSheetDialogFragment() {

    private var menuItems = mutableListOf<MenuOption>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        // Enable cancel on touch outside
        dialog.setCanceledOnTouchOutside(true)

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                // Set transparent background to show rounded corners
                it.setBackgroundResource(android.R.color.transparent)

                // Add margins
                val layoutParams = it.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(
                    0, // left margin in pixels
                    0,  // top margin
                    150, // right margin
                    0  // bottom margin
                )
                it.layoutParams = layoutParams

                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
                behavior.isFitToContents = true
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.bottom_sheet_menu, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerMenu)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        menuItems.addAll(listOf(
            MenuOption(1,R.drawable.ic_add_black_24dp, getString(R.string.new_conversation)),
            MenuOption(2,R.drawable.ic_outline_search_24, getString(R.string.main_menu_search)),
            MenuOption(3,R.drawable.ic_baseline_push_pin_24, getString(R.string.show_pinned)),
            MenuOption(4,R.drawable.ic_archive_white_24dp, getString(R.string.show_archived)),
            MenuOption(5,R.drawable.ic_block_white_24dp, getString(R.string.show_blocked)),
            MenuOption(6,R.drawable.ic_settings_black_24dp, getString(R.string.settings)),
            MenuOption(7,R.drawable.outline_mark_email_read_24, getString(R.string.mark_all_as_read))
        ))

        recycler.adapter = MenuAdapter(menuItems) {
            onMenuSelected(it)
            dismiss()
        }

        return view
    }

    object Constants {
        val TAG = "MainMenu"
    }
}