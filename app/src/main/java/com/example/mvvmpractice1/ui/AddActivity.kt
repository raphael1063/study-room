package com.example.mvvmpractice1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmpractice1.ContactViewModel
import com.example.mvvmpractice1.R
import com.example.mvvmpractice1.data.Contact
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null
    private var tag = "AddActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // intent null check & get extras
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER) && intent.hasExtra(EXTRA_CONTACT_ID)) { // 아이템을 클릭해 아이템의 정보가 넘어온 경우
            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim() //trim() 메소드는 문자열의 좌우 공백을 제거한다.
            val number = add_edittext_number.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase() //이름의 첫번째 글자를 대문자로 변환해 initial 에 저장한다.
                val contact = Contact(
                    id,
                    name,
                    number,
                    initial
                )
                contactViewModel.insert(contact)
                Log.e(tag, "입력된 정보를 context 객체에 넣어 contactViewModel 에 삽입")
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}
