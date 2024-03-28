from fastapi import FastAPI,APIRouter, Header
from fastapi.middleware.cors import CORSMiddleware
import jwt
import requests
from dto.register import RegisForm
from dto.user_dto import UserForm, Profile

from fastapi import FastAPI, Depends, HTTPException, status


app = FastAPI()


# Token Storage Instance
from dto.token_store import TokenStorage
token_storage = TokenStorage()

router = APIRouter()

# Enable CORS for cross-origin requests
# app.add_middleware(
#     CORSMiddleware,
#     allow_origins=["*"],
#     allow_credentials=True,
#     allow_methods=["*"],
#     allow_headers=["*"],
# )

# Define the URL of each service
SERVICE_URL = "http://localhost:53297"
# Create HTTPAdapter instances for each service
customer_services = requests.Session()
customer_services.headers.update({"Content-Type": "application/json"})
customer_services.mount(SERVICE_URL, requests.adapters.HTTPAdapter(pool_connections=5, pool_maxsize=5))


@router.post("/login/")
def login_user(username : str, password : str):
    url = f"{SERVICE_URL}/api/customers/login"
    payload = {
        "username" : username,
        "password" : password
    }
    response = customer_services.post(url, json=payload)
    if response.status_code == 200:
        # Lưu trữ access token khi đăng nhập thành công
        token_storage.token = (response.json()["accessToken"])
    response.raise_for_status()
    return response.json()

@router.post("/dashboard/")
def register_user(form : RegisForm):
    url = f"{SERVICE_URL}/api/dashboard"

    response = customer_services.post(url, json=form.dict())
    #response.raise_for_status()
    return response.status_code

@router.get("/orders/")
def get_all_user():
    url = f"{SERVICE_URL}/api/orders/getAll"
    
    headers = {"Authorization": f"Bearer {token_storage.token}"}
    response = customer_services.get(url, headers=headers)
    if response.raise_for_status() :
        return response.status_code
    #response.raise_for_status()
    return response.json()

@router.get("/categories/")
def get_by_user_id(id : int):
    url = f"{SERVICE_URL}/api/categories/all/"
    
    headers = {"Authorization": f"Bearer {token_storage.token}"}
    params = {'id': id}
    response = customer_services.get(url,params=params, headers=headers)
    response.raise_for_status()
    return response.json()

@router.put("/products/")
def edit_customer(form : UserForm):
    url = f"{SERVICE_URL}/api/products/all/"
    
    headers = {"Authorization": f"Bearer {token_storage.token}"}
    response = customer_services.put(url,json=form.dict(), headers=headers)
    response.raise_for_status()
    return response.status_code

@router.put("/edit-profile/")
def edit_customer(form : Profile):
    url = f"{SERVICE_URL}/api/customers/edit-profile/"
    
    headers = {"Authorization": f"Bearer {token_storage.token}"}
    response = customer_services.post(url,json=form.dict(), headers=headers)
    response.raise_for_status()
    return response.status_code


@router.put("/users/")
def edit_customer(form : Profile):
    url = f"{SERVICE_URL}/api/users/edit-all/"
    
    headers = {"Authorization": f"Bearer {token_storage.token}"}
    response = customer_services.post(url,json=form.dict(), headers=headers)
    response.raise_for_status()
    return response.status_code




# Add more routes for each service using the corresponding HTTPAdapter instance
