package com.example.mvvmpractice1

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmpractice1.data.Contact
import com.example.mvvmpractice1.data.ContactRepository

//AndroidViewModel 에서는 Application 을 파라미터로 사용한다. (Repository 를 통해서) Room 데이터베이스의 인스턴스를 만들 때에는 context 가 필요하다.
//하지만, 만약 ViewModel 이 액티비티의 context 를 쓰게 되면, 액티비티가 destroy 된 경우에는 메모리 릭이 발생할 수 있다.
//따라서 Application Context 를 사용하기 위해 Application 을 인자로 받는다.
//DB를 제어할 함수는 Repository 에 있는 함수를 이용해 설정해준다.

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val tag= "ContactViewModel"
    private val repository =
        ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
        Log.e(tag, "contact 객체를 contactRepository 에 삽입")
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}
