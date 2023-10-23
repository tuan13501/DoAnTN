export type UsernamePassword = {
  username: string;
  password: string;
}

export type SignInResponse = {
  loginToken: LoginToken;
  user: User;
}

export type LoginToken = {
  type: string;
  access_token: string;
  refresh_token: string;
}

export type User = {
  id: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  telephone: string;
}