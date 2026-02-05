using FluentValidation;
using FluentValidation.AspNetCore;
using Microsoft.EntityFrameworkCore;
using web_api.backend_infrastructure.Persistence;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
//builder.Services.AddOpenApi();

// Swagger (OpenAPI + UI)
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// ✅ DB: SQL Server
builder.Services.AddDbContext<InventarioDbContext>(options =>
{
    var cs = builder.Configuration.GetConnectionString("InventarioDb");
    options.UseSqlServer(cs, sql =>
    {
        sql.EnableRetryOnFailure(
            maxRetryCount: 5,
            maxRetryDelay: TimeSpan.FromSeconds(10),
            errorNumbersToAdd: null
        );
    });
});


// FluentValidation
builder.Services.AddFluentValidationAutoValidation();

// ✅ CORS ABIERTO (temporal, sin login)
builder.Services.AddCors(options =>
{
    options.AddPolicy("AngularDev", policy =>
           policy.WithOrigins("http://localhost:4200")
                 .AllowAnyHeader()
                 .AllowAnyMethod());
});
var app = builder.Build();



if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(); // UI en /swagger
}


//app.UseHttpsRedirection();

//app.UseAuthorization();


app.UseCors("AngularDev");


app.MapControllers();

app.Run();
