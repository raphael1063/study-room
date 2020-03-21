package com.example.mvvmpractice1.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

// ContactRepository.kt
//사실 Repository 에서 크게 정의하는 부분은 없다. 우선 Database, Dao, contacts 를 각각 초기화해준다.
//그리고 ViewModel 에서 DB에 접근을 요청할 때 수행할 함수를 만들어둔다.주의할 점은 Room DB를 메인 스레드에서 접근하려 하면 크래쉬가 발생한다. 에러 메세지는 다음과 같다.
//Cannot access database on the main thread since it may potentially lock the UI for a long period of time. (메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에, 메인 쓰레드에서는 데이터베이스에 접근할 수 없습니다.)
//따라서 별도의 스레드에서 Room 의 데이터에 접근해야 한다.

class ContactRepository(application: Application) {

    private val tag = "ContactRepository"
    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact) })
                Log.e(tag, "contactViewModel 로부터 받은 contact 객체를 contactDao 에 삽입. contact : $contact")
            thread.start()
        } catch (e: Exception) { }
    }

    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}