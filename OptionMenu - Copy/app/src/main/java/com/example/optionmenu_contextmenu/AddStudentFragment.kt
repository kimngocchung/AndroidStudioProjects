import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.optionmenu_contextmenu.R
import com.example.optionmenu_contextmenu.Student

class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val nameEditText = view.findViewById<EditText>(R.id.newName)
        val mssvEditText = view.findViewById<EditText>(R.id.newMSSV)

        btnSave.setOnClickListener {
            val name = nameEditText.text.toString()
            val mssv = mssvEditText.text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                // Tạo đối tượng sinh viên mới
                val newStudent = Student(name, mssv)

                // Thực hiện truyền dữ liệu về StudentListFragment
                val action = AddStudentFragmentDirections.actionAddStudentFragmentToStudentListFragment()
                findNavController().navigate(action)

                // Có thể dùng ViewModel hoặc giao tiếp trực tiếp để cập nhật dữ liệu nếu cần thiết
            }
        }
    }
}

class AddStudentFragmentDirections {
    companion object {
        fun actionAddStudentFragmentToStudentListFragment() {

        }
    }

}
