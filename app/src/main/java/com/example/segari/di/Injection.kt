package com.example.segari.di

import com.example.segari.data.SegariRepository

object Injection {
    fun provideRepository(): SegariRepository {
        return SegariRepository.getInstance()
    }
}