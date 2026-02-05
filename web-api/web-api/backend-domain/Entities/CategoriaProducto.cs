using web_api.backend_domain.Common;

namespace web_api.backend_domain.Entities
{
    public class CategoriaProducto : EntidadBase
    {
        public int Id { get; set; }
        public string Nombre { get; set; } = null!;
        public string? Descripcion { get; set; }
    }
}
