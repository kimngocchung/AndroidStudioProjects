import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.car.ui.toolbar.MenuItem
import com.example.optionmenu_contextmenu.R
import com.example.optionmenu_contextmenu.Student
import com.example.optionmenu_contextmenu.StudentAdapter

class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private lateinit var lv: ListView
    private val students = ArrayList<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lv = view.findViewById(R.id.lv)

        // Thêm sinh viên mẫu vào danh sách
        val names = listOf("Kim Ngọc Chung", "Nguyễn Quang Huy")
        val mssv = listOf("SV1001", "SV1002")

        for (i in names.indices) {
            students.add(Student(names[i], mssv[i]))
        }

        adapter = StudentAdapter(requireContext(), R.layout.layout_item, students)
        lv.adapter = adapter

        // Đăng ký Context Menu
        registerForContextMenu(lv)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        return when (item.itemId) {
            R.id.menu_edit -> {
                // Chuyển đến EditFragment với thông tin sinh viên
                val action = StudentListFragmentDirections.actionStudentListFragmentToEditStudentFragment(
                    students[position].name, students[position].mssv, position
                )
                findNavController().navigate(action)
                true
            }
            R.id.menu_remove -> {
                students.removeAt(position)
                adapter.notifyDataSetChanged()  // Cập nhật lại giao diện sau khi xóa
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Xử lý lựa chọn trong OptionsMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                val action = StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class StudentListFragmentDirections {
    companion object {
        fun actionStudentListFragmentToAddStudentFragment(): Any {

        }
    }

}
