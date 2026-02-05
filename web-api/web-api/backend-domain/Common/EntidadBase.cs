namespace web_api.backend_domain.Common
{
    public abstract class EntidadBase
    {
        public byte Estado { get; set; } = 1; // 1 activo, 0 inactivo
        public DateTime FechaCreacion { get; set; }
        public DateTime? FechaActualiza { get; set; }
    }
}
