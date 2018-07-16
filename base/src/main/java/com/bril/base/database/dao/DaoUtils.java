package com.bril.base.database.dao;

import android.content.Context;

/**
 * Created by SY on 2017/12/25.
 */

public class DaoUtils {
    /**
     * 此封装使用
     * 1.entity包中创建实例
     * eg：@Entity ！！！
     * public class Student {
     *
     * @Id ！！！必须有
     * private Long id;
     * public String name;
     * public int age;
     * }  =================== make project生成 xxxDao
     *
     * 2.编写xxxManager extends BaseDao<xxx> 复制已有即可，修改xxx名称
     *
     * 3.DaoUtils   添加新写的xxxManager
     * eg：public static UserManager getUserInstance(){
     * if (studentManager == null) {
     * studentManager = new UserManager(context);
     * }
     * return studentManager;
     * }
     * 4.使用 public class MainActivity extends AppCompatActivity {
     * public TextView textView;
     * @Override protected void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState);
     * setContentView(R.layout.activity_main);
     * textView=findViewById(R.id.content);
     * DaoUtils.init(this);
     * }
     * @Override protected void onDestroy() {
     * super.onDestroy();
     * DaoUtils.getUserInstance().CloseDataBase();
     * }
     * <p>
     * public void doClick(View view){
     * switch (view.getId()){
     * case R.id.z:
     * Student user=new Student();
     * user.setName("00000000000000");
     * DaoUtils.getStudentInstance().insertObject(user);
     * break;
     * case R.id.s:
     * DaoUtils.getStudentInstance().deleteAll(Student.class);
     * break;
     * case R.id.g:
     * Student userg=new Student();
     * userg.setId(2L);
     * userg.setName("1111111111111111111");
     * DaoUtils.getStudentInstance().updateObject(userg);
     * break;
     * case R.id.c:
     * String name="";
     * textView.setText("");
     * List<Student> users=DaoUtils.getStudentInstance().QueryAll(Student.class);
     * for (Student user1:users){
     * name+=user1.getId()+":"+user1.getName()+"\n";
     * }
     * textView.setText(name);
     * break;
     * }
     * }
     * }
     */

    private static UserManager studentManager;
    public static Context context;

    public static void init(Context context) {
        DaoUtils.context = context.getApplicationContext();
    }


    /**
     * 单列模式获取StudentManager对象
     *
     * @return
     */
    public static UserManager getUserInstance() {
        if (studentManager == null) {
            studentManager = new UserManager(context);
        }
        return studentManager;
    }
}