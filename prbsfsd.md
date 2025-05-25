# 🧩 Problems Faced & Solutions – RedKart Project

This file documents the key issues and blockers encountered during the development of the RedKart project, along with their respective solutions and notes.

---

## 🛠️ Phase 1 Debug Log & Resolution Notes

**🐞 Issues Faced:**

* N/A: Project initialized successfully without any configuration errors or blocking issues.

✅ Outcome: Project booted with Spring login screen on port 8090.

---

## 🛠️ Phase 2 Debug Log & Resolution Notes

**🐞 Issues Faced:**

* N/A: Home controller and Thymeleaf view setup worked smoothly.

✅ Outcome: Home page rendered correctly at `/` without login prompt (security disabled temporarily).

---

## 🛠️ Phase 3 Debug Log & Resolution Notes

**🐞 Issues Faced:**

* N/A: Product model, repository, and seeding logic all worked as expected.

✅ Outcome: Products loaded and displayed on the homepage successfully.

---

## 🛠️ Phase 4 Debug Log & Resolution Notes

### 🐞 1. Infinite Redirect Loop on Login

**Error:** ERR\_TOO\_MANY\_REDIRECTS

**Symptoms:**

* Repeated `/login` requests
* Browser kept redirecting to `/login` without proceeding

**Cause:**

* Missing GET mapping for `/login` page
* Login form didn't properly match expected field names (`username`, `password`)

**Fix:**

```java
@GetMapping("/login")
public String showLoginForm() {
    return "login";
}
```

And in `login.html`:

* Used `method="POST"`
* Input fields named `username` and `password`
* Included CSRF token via:

```html
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
```

✅ Resolved and login now processes credentials correctly.

---

### 🐞 2. H2 Console 403 Forbidden Error

**Error:** HTTP 403 (Forbidden)

**Cause:**

* Spring Security blocked `/h2-console/**`
* Frame embedding for H2 was also blocked

**Fix (in SecurityConfig):**

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/h2-console/**", "/login", "/register", "/css/**", "/").permitAll()
    .anyRequest().authenticated())
.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
.headers(headers -> headers.frameOptions().disable())
```

⚠️ `headers().disable()` is dev-only — enables iframes for H2 console.

✅ Console accessible at `/h2-console` again.

---

### 🐞 3. Duplicate PasswordEncoder Bean

**Error:**

```text
BeanDefinitionOverrideException: Cannot register bean definition 'passwordEncoder'
```

**Cause:**

* Password encoder bean was defined in both `SecurityBeans` and `SecurityConfig`

**Fix:**

* Removed the duplicate method from `SecurityConfig`
* Reused existing bean from `SecurityBeans`

✅ App boots without bean conflicts.

---

### 🐞 4. Login Form Not Authenticating Users

**Problem:** Login form submits but user remains on same page (no errors shown)

**Cause:**

* Passwords in DB were stored in plaintext
* Spring Security expected encoded passwords
* Input field names did not match default `username` and `password`

**Fix:**

* Used `BCryptPasswordEncoder` for hashing passwords during registration
* Updated form field names

✅ Login now authenticates DB users correctly.

---

## ✅ Phase 4 Outcome

* Login page working ✅
* Credentials verified from DB ✅
* H2 console unblocked ✅
* CSRF and frame handling addressed ✅

### 📦 Git Push (Optional)

Yes, pushed the checkpoint after resolving major auth and console blockers.

```bash
git add .
git commit -m "fix: login redirect loop and H2 access issue"
git push origin main
```

✅ Phase 4 fully complete.

---

✅ Ready to proceed to Phase 5.
