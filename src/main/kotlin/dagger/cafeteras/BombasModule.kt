package dagger.cafeteras

import dagger.Binds
import dagger.Module

@Module
internal abstract class BombasModule {
    @Binds // No devuelvo nada, solo bindeo el objeto,por eso no privider
    abstract fun providePump(bomba: Termosifon): Bomba
}