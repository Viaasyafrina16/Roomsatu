package com.example.roomsatu.repositori

import com.example.roomsatu.room.Siswa
import com.example.roomsatu.repositori.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RepositoriSiswa {
    fun getAllSiswaSteam(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositoriSiswa(
    private val siswaDao: SiswaDao
): RepositoriSiswa {
    override fun getAllSiswaSteam(): Flow<List<Siswa>> = SiswaDao.getALlSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = SiswaDao.insert(siswa)
}