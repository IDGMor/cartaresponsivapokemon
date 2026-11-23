# Carta Responsiva Pokémon TCG ⚡🔥🍃

Una aplicación Android moderna desarrollada con **Jetpack Compose** y **Material 3** que recrea cartas coleccionables responsivas de Pokémon TCG (estilo Edición 30th Anniversary), con soporte para múltiples cartas, internacionalización (`strings.xml`) y una galería interactiva en carrusel.

---

## 🚀 Características Principales

1. **Diseño de Carta Altamente Responsivo (`BoxWithConstraints`)**:
   - Mantiene la relación de aspecto clásica de las cartas TCG (~2.5:3.5) adaptándose perfectamente a cualquier tamaño de pantalla (móviles, tablets, modo vertical u horizontal).
   - Estilizado con bordes dorados degradados, marcos para imágenes y tipografías cuidadas.

2. **Galería TCG Interactiva (`HorizontalPager`)**:
   - Carrusel deslizable inspirado en las galerías oficiales de Pokémon TCG.
   - Animaciones de profundidad y enfoque dinámico (*scale* y *alpha*) para resaltar la carta seleccionada.
   - Indicadores de página (*dots*) y contador flotante en tiempo real (*"Carta X de Y"*).

3. **Gestión Dinámica de Colección**:
   - Incluye múltiples Pokémon preconfigurados: **Pikachu**, **Charmander**, **Bulbasaur**, **Squirtle** y **Snorlax**.
   - Cada carta cuenta con sus propios PS, tipos, descripciones, ataques y estadísticas de debilidad/resistencia.

4. **Internacionalización y Recursos Limpios**:
   - Todos los textos estáticos e información de los Pokémon están extraídos en `strings.xml` para facilitar la localización y mantenimiento.

---

## 📱 Tecnologías y Librerías Utilizadas

- **Kotlin** & **Jetpack Compose** (UI Declarativa).
- **Material 3** (Componentes de diseño modernos, `Scaffold`, `TopAppBar`, `Card`).
- **Compose Foundation Pager** (`HorizontalPager`) para el carrusel de la galería.
- **Edge-to-Edge** para una inmersión visual completa en pantallas modernas de Android.

---

## 🛠️ Cómo Ejecutar el Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/IDGMor/cartaresponsivapokemon.git
   ```
2. Abre el proyecto en **Android Studio** (Koala o superior).
3. Sincroniza el proyecto con Gradle (`Sync Project with Gradle Files`).
4. Ejecuta la app en un emulador o dispositivo físico con Android 7.0 (API 24) o superior.

---

## 📄 Licencia

Este proyecto es de código abierto para fines educativos y de práctica con Jetpack Compose.
