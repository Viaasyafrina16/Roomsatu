package com.example.roomsatu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.roomsatu.repositori.RepositoriSiswa
import com.example.roomsatu.room.Siswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    var uiStateSiswa by mutableStateOf(value = UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUIState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(uiState = detailSiswa))
    }

    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositoriSiswa.insertSiswa(siswa = uiStateSiswa.detailSiswa.toSiswa())
        }
    }

    data class UIStateSiswa(
        val detailSiswa: DetailSiswa = DetailSiswa(),
        val isEntryValid: Boolean = false
    )

    data class DetailSiswa(
        val id: Int = 0,
        val nama: String = "",
        val alamat: String = "",
        val telpon: String = ""
    )

    fun DetailSiswa.toSiswa(): Siswa = Siswa(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telpon
    )

    fun Siswa.toUIStateSiswa(isEntryValid: Boolean = false): UIStateSiswa = UIStateSiswa(
        detailSiswa = this.toDetailSiswa(),
        isEntryValid = isEntryValid
    )

    fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telpon
    )
}