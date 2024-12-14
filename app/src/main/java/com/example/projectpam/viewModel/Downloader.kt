package com.example.projectpam.viewModel

interface Downloader {
    fun downloadFile(url: String): Long
}