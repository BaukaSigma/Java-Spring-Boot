// Система многоязычности для Learning Progress Tracker
const translations = {
    en: {
        // Общие
        'app.name': 'Learning Progress Tracker',
        'app.welcome': 'Welcome',
        'app.logout': 'Logout',
        'app.login': 'Login',
        'app.register': 'Register',
        'app.loading': 'Loading...',
        'app.error': 'Error',
        'app.success': 'Success',
        'app.save': 'Save',
        'app.cancel': 'Cancel',
        'app.delete': 'Delete',
        'app.edit': 'Edit',
        'app.create': 'Create',
        'app.search': 'Search',
        'app.filter': 'Filter',
        'app.actions': 'Actions',
        'app.status': 'Status',
        'app.date': 'Date',
        'app.noData': 'No data',
        
        // Навигация
        'nav.dashboard': 'Dashboard',
        'nav.courses': 'Courses',
        'nav.students': 'Students',
        'nav.grades': 'Grades',
        'nav.attendance': 'Attendance',
        'nav.profile': 'Profile',
        
        // Авторизация
        'auth.username': 'Username',
        'auth.password': 'Password',
        'auth.email': 'Email',
        'auth.firstName': 'First Name',
        'auth.lastName': 'Last Name',
        'auth.role': 'Role',
        'auth.loginTitle': 'Login to Your Account',
        'auth.registerTitle': 'Create New Account',
        'auth.loginButton': 'Login',
        'auth.registerButton': 'Register',
        'auth.noAccount': "Don't have an account?",
        'auth.haveAccount': 'Already have an account?',
        'auth.registerLink': 'Register here',
        'auth.loginLink': 'Login here',
        
        // Админ панель
        'admin.title': 'Admin Dashboard',
        'admin.subtitle': 'Manage users, courses, and system settings',
        'admin.users': 'Users',
        'admin.courses': 'Courses',
        'admin.totalUsers': 'Total Users',
        'admin.totalCourses': 'Total Courses',
        'admin.activeStudents': 'Active Students',
        'admin.instructors': 'Instructors',
        'admin.userManagement': 'User Management',
        'admin.courseManagement': 'Course Management',
        'admin.createUser': 'Create User',
        'admin.createCourse': 'Create Course',
        'admin.deleteUser': 'Delete User',
        'admin.deleteCourse': 'Delete Course',
        'admin.confirmDelete': 'Are you sure you want to delete?',
        
        // Преподаватель
        'instructor.title': 'Instructor Dashboard',
        'instructor.subtitle': 'Manage courses, assignments, and student grades',
        'instructor.myCourses': 'My Courses',
        'instructor.students': 'Students',
        'instructor.assignments': 'Assignments',
        'instructor.avgGrade': 'Average Grade',
        'instructor.createCourse': 'Create Course',
        'instructor.createAssignment': 'Create Assignment',
        'instructor.assignCourse': 'Assign Course',
        'instructor.gradeStudent': 'Grade Student',
        'instructor.allStudents': 'All Students',
        'instructor.assignCourseToStudent': 'Assign Course to Student',
        'instructor.gradeAssignment': 'Grade Assignment',
        
        // Студент
        'student.title': 'Student Dashboard',
        'student.subtitle': 'Track your courses, grades, and attendance',
        'student.myCourses': 'My Courses',
        'student.myGrades': 'My Grades',
        'student.myAttendance': 'My Attendance',
        'student.upcomingDeadlines': 'Upcoming Deadlines',
        'student.recentGrades': 'Recent Grades',
        'student.avgGrade': 'Average Grade',
        'student.attendance': 'Attendance',
        'student.coursesEnrolled': 'Courses Enrolled',
        'student.noGrades': 'No grades yet',
        'student.noAttendance': 'No attendance data',
        'student.noDeadlines': 'No upcoming deadlines',
        'student.noCourses': 'You are not enrolled in any courses',
        'student.daysLeft': 'days left',
        
        // Курсы
        'course.code': 'Course Code',
        'course.title': 'Course Title',
        'course.titleEn': 'Title (EN)',
        'course.titleRu': 'Title (RU)',
        'course.titleKk': 'Title (KK)',
        'course.description': 'Description',
        'course.descriptionEn': 'Description (EN)',
        'course.descriptionRu': 'Description (RU)',
        'course.descriptionKk': 'Description (KK)',
        'course.credits': 'Credits',
        'course.instructor': 'Instructor',
        'course.students': 'Students',
        'course.status': 'Status',
        'course.active': 'Active',
        'course.completed': 'Completed',
        'course.dropped': 'Dropped',
        
        // Задания
        'assignment.title': 'Assignment',
        'assignment.titleEn': 'Title (EN)',
        'assignment.titleRu': 'Title (RU)',
        'assignment.titleKk': 'Title (KK)',
        'assignment.description': 'Description',
        'assignment.maxScore': 'Max Score',
        'assignment.deadline': 'Deadline',
        'assignment.dueDate': 'Due Date',
        'assignment.submitted': 'Submitted',
        'assignment.graded': 'Graded',
        'assignment.score': 'Score',
        'assignment.feedback': 'Feedback',
        
        // Оценки
        'grade.score': 'Score',
        'grade.maxScore': 'Max Score',
        'grade.percentage': 'Percentage',
        'grade.feedback': 'Feedback',
        'grade.gradedAt': 'Graded At',
        'grade.submittedAt': 'Submitted At',
        'grade.comment': 'Comment',
        
        // Посещаемость
        'attendance.date': 'Date',
        'attendance.status': 'Status',
        'attendance.present': 'Present',
        'attendance.absent': 'Absent',
        'attendance.late': 'Late',
        'attendance.excused': 'Excused',
        'attendance.notes': 'Notes',
        'attendance.percentage': 'Attendance Percentage',
    },
    
    ru: {
        // Общие
        'app.name': 'Трекер Учебного Прогресса',
        'app.welcome': 'Добро пожаловать',
        'app.logout': 'Выход',
        'app.login': 'Вход',
        'app.register': 'Регистрация',
        'app.loading': 'Загрузка...',
        'app.error': 'Ошибка',
        'app.success': 'Успешно',
        'app.save': 'Сохранить',
        'app.cancel': 'Отмена',
        'app.delete': 'Удалить',
        'app.edit': 'Редактировать',
        'app.create': 'Создать',
        'app.search': 'Поиск',
        'app.filter': 'Фильтр',
        'app.actions': 'Действия',
        'app.status': 'Статус',
        'app.date': 'Дата',
        'app.noData': 'Нет данных',
        
        // Навигация
        'nav.dashboard': 'Панель управления',
        'nav.courses': 'Курсы',
        'nav.students': 'Студенты',
        'nav.grades': 'Оценки',
        'nav.attendance': 'Посещаемость',
        'nav.profile': 'Профиль',
        
        // Авторизация
        'auth.username': 'Имя пользователя',
        'auth.password': 'Пароль',
        'auth.email': 'Email',
        'auth.firstName': 'Имя',
        'auth.lastName': 'Фамилия',
        'auth.role': 'Роль',
        'auth.loginTitle': 'Вход в аккаунт',
        'auth.registerTitle': 'Создать новый аккаунт',
        'auth.loginButton': 'Войти',
        'auth.registerButton': 'Зарегистрироваться',
        'auth.noAccount': 'Нет аккаунта?',
        'auth.haveAccount': 'Уже есть аккаунт?',
        'auth.registerLink': 'Зарегистрируйтесь здесь',
        'auth.loginLink': 'Войдите здесь',
        
        // Админ панель
        'admin.title': 'Панель администратора',
        'admin.subtitle': 'Управление пользователями, курсами и настройками системы',
        'admin.users': 'Пользователи',
        'admin.courses': 'Курсы',
        'admin.totalUsers': 'Всего пользователей',
        'admin.totalCourses': 'Всего курсов',
        'admin.activeStudents': 'Активных студентов',
        'admin.instructors': 'Преподавателей',
        'admin.userManagement': 'Управление пользователями',
        'admin.courseManagement': 'Управление курсами',
        'admin.createUser': 'Создать пользователя',
        'admin.createCourse': 'Создать курс',
        'admin.deleteUser': 'Удалить пользователя',
        'admin.deleteCourse': 'Удалить курс',
        'admin.confirmDelete': 'Вы уверены, что хотите удалить?',
        
        // Преподаватель
        'instructor.title': 'Панель преподавателя',
        'instructor.subtitle': 'Управляйте курсами, заданиями и оценками студентов',
        'instructor.myCourses': 'Мои курсы',
        'instructor.students': 'Студентов',
        'instructor.assignments': 'Заданий',
        'instructor.avgGrade': 'Средний балл',
        'instructor.createCourse': 'Создать курс',
        'instructor.createAssignment': 'Создать задание',
        'instructor.assignCourse': 'Назначить курс',
        'instructor.gradeStudent': 'Оценка',
        'instructor.allStudents': 'Все студенты',
        'instructor.assignCourseToStudent': 'Назначить курс студенту',
        'instructor.gradeAssignment': 'Выставить оценку',
        
        // Студент
        'student.title': 'Панель студента',
        'student.subtitle': 'Отслеживайте свои курсы, оценки и посещаемость',
        'student.myCourses': 'Мои курсы',
        'student.myGrades': 'Мои оценки',
        'student.myAttendance': 'Моя посещаемость',
        'student.upcomingDeadlines': 'Предстоящие дедлайны',
        'student.recentGrades': 'Последние оценки',
        'student.avgGrade': 'Средний балл',
        'student.attendance': 'Посещаемость',
        'student.coursesEnrolled': 'Записано на курсы',
        'student.noGrades': 'Оценок пока нет',
        'student.noAttendance': 'Данных о посещаемости нет',
        'student.noDeadlines': 'Нет предстоящих дедлайнов',
        'student.noCourses': 'Вы пока не записаны ни на один курс',
        'student.daysLeft': 'дн.',
        
        // Курсы
        'course.code': 'Код курса',
        'course.title': 'Название курса',
        'course.titleEn': 'Название (EN)',
        'course.titleRu': 'Название (RU)',
        'course.titleKk': 'Название (KK)',
        'course.description': 'Описание',
        'course.descriptionEn': 'Описание (EN)',
        'course.descriptionRu': 'Описание (RU)',
        'course.descriptionKk': 'Описание (KK)',
        'course.credits': 'Кредиты',
        'course.instructor': 'Преподаватель',
        'course.students': 'Студенты',
        'course.status': 'Статус',
        'course.active': 'Активный',
        'course.completed': 'Завершен',
        'course.dropped': 'Отменен',
        
        // Задания
        'assignment.title': 'Задание',
        'assignment.titleEn': 'Название (EN)',
        'assignment.titleRu': 'Название (RU)',
        'assignment.titleKk': 'Название (KK)',
        'assignment.description': 'Описание',
        'assignment.maxScore': 'Макс. балл',
        'assignment.deadline': 'Дедлайн',
        'assignment.dueDate': 'Срок сдачи',
        'assignment.submitted': 'Сдано',
        'assignment.graded': 'Оценено',
        'assignment.score': 'Оценка',
        'assignment.feedback': 'Отзыв',
        
        // Оценки
        'grade.score': 'Оценка',
        'grade.maxScore': 'Макс. балл',
        'grade.percentage': 'Процент',
        'grade.feedback': 'Отзыв',
        'grade.gradedAt': 'Дата оценки',
        'grade.submittedAt': 'Дата сдачи',
        'grade.comment': 'Комментарий',
        
        // Посещаемость
        'attendance.date': 'Дата',
        'attendance.status': 'Статус',
        'attendance.present': 'Присутствовал',
        'attendance.absent': 'Отсутствовал',
        'attendance.late': 'Опоздал',
        'attendance.excused': 'Уважительная причина',
        'attendance.notes': 'Заметки',
        'attendance.percentage': 'Процент посещаемости',
    },
    
    kk: {
        // Общие
        'app.name': 'Оқу Прогресін Бақылау',
        'app.welcome': 'Қош келдіңіз',
        'app.logout': 'Шығу',
        'app.login': 'Кіру',
        'app.register': 'Тіркелу',
        'app.loading': 'Жүктелуде...',
        'app.error': 'Қате',
        'app.success': 'Сәтті',
        'app.save': 'Сақтау',
        'app.cancel': 'Болдырмау',
        'app.delete': 'Жою',
        'app.edit': 'Өңдеу',
        'app.create': 'Жасау',
        'app.search': 'Іздеу',
        'app.filter': 'Сүзгі',
        'app.actions': 'Әрекеттер',
        'app.status': 'Мәртебе',
        'app.date': 'Күн',
        'app.noData': 'Деректер жоқ',
        
        // Навигация
        'nav.dashboard': 'Басқару тақтасы',
        'nav.courses': 'Курстар',
        'nav.students': 'Студенттер',
        'nav.grades': 'Бағалар',
        'nav.attendance': 'Қатысу',
        'nav.profile': 'Профиль',
        
        // Авторизация
        'auth.username': 'Пайдаланушы аты',
        'auth.password': 'Құпия сөз',
        'auth.email': 'Email',
        'auth.firstName': 'Аты',
        'auth.lastName': 'Тегі',
        'auth.role': 'Рөл',
        'auth.loginTitle': 'Аккаунтқа кіру',
        'auth.registerTitle': 'Жаңа аккаунт жасау',
        'auth.loginButton': 'Кіру',
        'auth.registerButton': 'Тіркелу',
        'auth.noAccount': 'Аккаунт жоқ па?',
        'auth.haveAccount': 'Аккаунт бар ма?',
        'auth.registerLink': 'Мұнда тіркеліңіз',
        'auth.loginLink': 'Мұнда кіріңіз',
        
        // Админ панель
        'admin.title': 'Әкімші тақтасы',
        'admin.subtitle': 'Пайдаланушыларды, курстарды және жүйе параметрлерін басқару',
        'admin.users': 'Пайдаланушылар',
        'admin.courses': 'Курстар',
        'admin.totalUsers': 'Барлық пайдаланушылар',
        'admin.totalCourses': 'Барлық курстар',
        'admin.activeStudents': 'Белсенді студенттер',
        'admin.instructors': 'Оқытушылар',
        'admin.userManagement': 'Пайдаланушыларды басқару',
        'admin.courseManagement': 'Курстарды басқару',
        'admin.createUser': 'Пайдаланушы жасау',
        'admin.createCourse': 'Курс жасау',
        'admin.deleteUser': 'Пайдаланушыны жою',
        'admin.deleteCourse': 'Курсты жою',
        'admin.confirmDelete': 'Жоюға сенімдісіз бе?',
        
        // Преподаватель
        'instructor.title': 'Оқытушы тақтасы',
        'instructor.subtitle': 'Курстарды, тапсырмаларды және студент бағаларын басқару',
        'instructor.myCourses': 'Менің курстарым',
        'instructor.students': 'Студенттер',
        'instructor.assignments': 'Тапсырмалар',
        'instructor.avgGrade': 'Орташа балл',
        'instructor.createCourse': 'Курс жасау',
        'instructor.createAssignment': 'Тапсырма жасау',
        'instructor.assignCourse': 'Курс тағайындау',
        'instructor.gradeStudent': 'Баға',
        'instructor.allStudents': 'Барлық студенттер',
        'instructor.assignCourseToStudent': 'Студентке курс тағайындау',
        'instructor.gradeAssignment': 'Баға қою',
        
        // Студент
        'student.title': 'Студент тақтасы',
        'student.subtitle': 'Курстарыңызды, бағаларыңызды және қатысуыңызды қадағалаңыз',
        'student.myCourses': 'Менің курстарым',
        'student.myGrades': 'Менің бағаларым',
        'student.myAttendance': 'Менің қатысуым',
        'student.upcomingDeadlines': 'Алдағы мерзімдер',
        'student.recentGrades': 'Соңғы бағалар',
        'student.avgGrade': 'Орташа балл',
        'student.attendance': 'Қатысу',
        'student.coursesEnrolled': 'Курстарға жазылған',
        'student.noGrades': 'Бағалар әлі жоқ',
        'student.noAttendance': 'Қатысу деректері жоқ',
        'student.noDeadlines': 'Алдағы мерзімдер жоқ',
        'student.noCourses': 'Сіз әлі ешбір курсқа жазылмағансыз',
        'student.daysLeft': 'күн',
        
        // Курсы
        'course.code': 'Курс коды',
        'course.title': 'Курс атауы',
        'course.titleEn': 'Атауы (EN)',
        'course.titleRu': 'Атауы (RU)',
        'course.titleKk': 'Атауы (KK)',
        'course.description': 'Сипаттама',
        'course.descriptionEn': 'Сипаттама (EN)',
        'course.descriptionRu': 'Сипаттама (RU)',
        'course.descriptionKk': 'Сипаттама (KK)',
        'course.credits': 'Кредиттер',
        'course.instructor': 'Оқытушы',
        'course.students': 'Студенттер',
        'course.status': 'Мәртебе',
        'course.active': 'Белсенді',
        'course.completed': 'Аяқталған',
        'course.dropped': 'Тоқтатылған',
        
        // Задания
        'assignment.title': 'Тапсырма',
        'assignment.titleEn': 'Атауы (EN)',
        'assignment.titleRu': 'Атауы (RU)',
        'assignment.titleKk': 'Атауы (KK)',
        'assignment.description': 'Сипаттама',
        'assignment.maxScore': 'Макс. балл',
        'assignment.deadline': 'Мерзім',
        'assignment.dueDate': 'Тапсыру мерзімі',
        'assignment.submitted': 'Тапсырылды',
        'assignment.graded': 'Бағаланды',
        'assignment.score': 'Баға',
        'assignment.feedback': 'Пікір',
        
        // Оценки
        'grade.score': 'Баға',
        'grade.maxScore': 'Макс. балл',
        'grade.percentage': 'Пайыз',
        'grade.feedback': 'Пікір',
        'grade.gradedAt': 'Бағалау күні',
        'grade.submittedAt': 'Тапсыру күні',
        'grade.comment': 'Түсініктеме',
        
        // Посещаемость
        'attendance.date': 'Күн',
        'attendance.status': 'Мәртебе',
        'attendance.present': 'Қатысты',
        'attendance.absent': 'Қатыспады',
        'attendance.late': 'Кешікті',
        'attendance.excused': 'Дәлелді себеп',
        'attendance.notes': 'Ескертпелер',
        'attendance.percentage': 'Қатысу пайызы',
    }
};

// Текущий язык (по умолчанию русский)
let currentLang = localStorage.getItem('language') || 'ru';

// Функция для получения перевода
function t(key) {
    return translations[currentLang][key] || key;
}

// Функция для смены языка
function changeLanguage(lang) {
    if (!translations[lang]) {
        console.error(`Language ${lang} not found`);
        return;
    }
    
    currentLang = lang;
    localStorage.setItem('language', lang);
    
    // Обновляем все элементы с атрибутом data-i18n
    document.querySelectorAll('[data-i18n]').forEach(element => {
        const key = element.getAttribute('data-i18n');
        const translation = t(key);
        
        if (element.tagName === 'INPUT' || element.tagName === 'TEXTAREA') {
            element.placeholder = translation;
        } else {
            element.textContent = translation;
        }
    });
    
    // Обновляем активный язык в переключателе
    document.querySelectorAll('.lang-btn').forEach(btn => {
        btn.classList.remove('active');
        if (btn.getAttribute('data-lang') === lang) {
            btn.classList.add('active');
        }
    });
    
    // Вызываем событие смены языка
    window.dispatchEvent(new CustomEvent('languageChanged', { detail: { language: lang } }));
}

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    changeLanguage(currentLang);
});

// Экспорт для использования в других скриптах
window.i18n = {
    t,
    changeLanguage,
    getCurrentLanguage: () => currentLang,
    translations
};
