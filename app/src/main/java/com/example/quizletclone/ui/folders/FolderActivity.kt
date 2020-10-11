package com.example.quizletclone.ui.folders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizletclone.R
import com.example.quizletclone.databinding.ActivityFolderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_set.*


@AndroidEntryPoint
class FolderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_folder)
        binding.lifecycleOwner = this


        binding.folderActivityToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}