# ProductApp

ProductApp es una aplicación de Android para la administración de productos que permite crear, leer, actualizar y eliminar productos en una base de datos de Firebase Firestore. Los productos son identificados mediante un SKU amigable que facilita su manipulación.

## Funcionalidades

- **Crear Producto**: Los usuarios pueden agregar nuevos productos ingresando un SKU, nombre, marca, precio, stock y descripción.
- **Leer Productos**: Visualiza una lista completa de productos almacenados en Firebase, incluyendo detalles como SKU, nombre, marca, precio, stock y descripción.
- **Actualizar Producto**: Permite modificar la información de un producto existente buscando por su SKU.
- **Eliminar Producto**: Los usuarios pueden eliminar un producto ingresando su SKU y confirmando la acción.

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal para el desarrollo de la aplicación.
- **Firebase Firestore**: Base de datos NoSQL en la nube para el almacenamiento y la gestión de productos.
- **Android Studio**: Entorno de desarrollo integrado (IDE) para la creación de la aplicación.
- **Material Design**: Interfaz moderna y fácil de usar para la aplicación.

## Configuración del Proyecto

Para ejecutar este proyecto en tu máquina local, sigue estos pasos:

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/brypro/ProductApp-droid.git
   cd ProductApp

2. **Configurar Firebase**:
    - Crea un proyecto en Firebase y habilita Firestore en modo de prueba.
    - Descarga el archivo `google-services.json` desde la consola de Firebase.
    - Coloca `google-services.json` en el directorio `app` de tu proyecto.

3. **Sincronizar Dependencias**:
    - Abre el proyecto en Android Studio.
    - Sincroniza el proyecto con Gradle para asegurarte de que todas las dependencias estén instaladas.

## Estructura del Proyecto

- `MainActivity`: Pantalla de inicio de la aplicación que consiste en el login de usuario.
- `HomeActivity`: Pantalla principal de la aplicación con un menú de opciones.
- `CreateActivity`: Pantalla para añadir nuevos productos a la base de datos.
- `ReadActivity`: Pantalla que muestra la lista de productos y sus detalles.
- `UpdateActivity`: Pantalla que permite modificar los detalles de un producto.
- `DeleteActivity`: Pantalla que permite buscar y eliminar productos por SKU.
- `ProductAdapter`: Adaptador para mostrar los productos en un `RecyclerView` en `ReadActivity`.
- `Product`: Modelo de datos para representar un producto en la aplicación.

## Uso de la Aplicación

1. **Crear un Producto**:
    - Navega a la sección de creación de producto.
    - Ingresa los detalles del producto, incluyendo el SKU.
    - Guarda el producto, que será almacenado en Firestore.

2. **Leer Productos**:
    - Ve a la lista de productos para ver todos los productos almacenados.
    - La información mostrada incluye el SKU, nombre, marca, precio, stock y descripción.

3. **Actualizar un Producto**:
    - Ingresa el SKU del producto que deseas actualizar.
    - Carga la información del producto y modifica los campos que necesites.
    - Guarda los cambios, que se actualizarán en Firestore.

4. **Eliminar un Producto**:
    - Ingresa el SKU del producto que deseas eliminar.
    - Confirma la eliminación para remover el producto de Firestore.

    

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.
