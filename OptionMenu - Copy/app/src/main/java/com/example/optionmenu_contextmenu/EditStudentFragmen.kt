import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.optionmenu_contextmenu.R

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private var position: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<EditText>(R.id.editName)
        val mssvEditText = view.findViewById<EditText>(R.id.editMSSV)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)

        val name = arguments?.getString("name")
        val mssv = arguments?.getString("mssv")
        position = arguments?.getInt("position", -1) ?: -1

        nameEditText.setText(name)
        mssvEditText.setText(mssv)

        btnUpdate.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedMssv = mssvEditText.text.toString()

            if (updatedName.isNotEmpty() && updatedMssv.isNotEmpty() && position >= 0) {
                val action = EditStudentFragmentDirections.actionEditStudentFragmentToStudentListFragment()
                findNavController().navigate(action)
            }
        }
    }
}

class EditStudentFragmentDirections {
    companion object {
        fun actionEditStudentFragmentToStudentListFragment() {

        }
    }

}
