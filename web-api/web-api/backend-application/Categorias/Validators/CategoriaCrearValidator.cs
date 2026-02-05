using web_api.backend_application.Categorias.Dtos;
using FluentValidation;

namespace web_api.backend_application.Categorias.Validators
{
    public class CategoriaCrearValidator : AbstractValidator<CategoriaCrearRequest>
    {
        public CategoriaCrearValidator()
        {
            RuleFor(x => x.Nombre)
                .NotEmpty().MinimumLength(2).MaximumLength(150);

            RuleFor(x => x.Descripcion)
                .MaximumLength(500);

            RuleFor(x => x.Estado)
                .Must(x => x == 0 || x == 1);
        }
    }
}
