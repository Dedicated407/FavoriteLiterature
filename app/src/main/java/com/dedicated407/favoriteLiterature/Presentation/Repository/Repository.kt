package com.dedicated407.favoriteLiterature.Presentation.Repository

import android.app.Application
import com.dedicated407.favoriteLiterature.Presentation.Repository.Mock.MockBase
import com.dedicated407.favoriteLiterature.Presentation.Room.BookRepository


class Repository(application: Application) {

    init {
        if (IRepository == null) {
            IRepository = BookRepository(application)
        }
    }

    companion object{
        private var IRepository: IRepositoryTasks? = null

        fun getRepository(): IRepositoryTasks{
            if (IRepository == null) {
                IRepository = MockBase()
            }
            return IRepository as IRepositoryTasks
        }
    }
}