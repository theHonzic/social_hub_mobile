//
//  InitialLoginView.swift
//  iosApp
//
//  Created by Jan Janovec on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct InitialLoginView: View {
    @Binding var authPage: AuthPage
    @StateObject var viewModel: LoginVM = .init()
    @EnvironmentObject private var settings: GlobalSettingsVM
    @State var login: String = ""
    @State var password: String = ""
    var loginButtonDisabled: Bool {
        login.isEmpty || password.isEmpty
    }
    var body: some View {
        ZStack {
            VStack(alignment: .leading, spacing: 14) {
                HStack {
                    VStack(alignment: .leading, spacing: 20) {
                        Text("Welcome to the Social Hub")
                            .font(.largeTitle)
                            .fontWeight(.heavy)
                            .foregroundStyle(Color.white)
                            .padding(.top, 30)
                        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt nisi vel nulla vestibulum aliquet. Mauris non lectus ut erat lobortis egestas.")
                            .foregroundStyle(Color.gray)
                    }
                    Spacer()
                }
                SingleLineTextField(text: $login, placeholder: "Login", background: .ultraThinMaterial, foreground: .ultraThickMaterial, icon: .init(systemName: "person"))
                SingleLineTextField(text: $password, placeholder: "Password", background: .ultraThinMaterial, foreground: .ultraThickMaterial, icon: .init(systemName: "lock"))
                HStack(alignment: .center, spacing: 4) {
                    Text("Forgot your password?")
                        .foregroundStyle(Color.white)
                    Button("Reset it here.") {
                        authPage = .register
                    }
                    .tint(.orange)
                }
                .font(.callout)
                .padding(.horizontal, 14)
                Spacer()
                if viewModel.state.state is LoginContractILoginStateLOADING {
                    HStack {
                        Spacer()
                        ProgressView()
                            .tint(.orange)
                        Spacer()
                    }
                    Spacer()
                }
            }
            VStack(alignment: .center, spacing: 14) {
                Spacer()
                RoundedButton(text: "Login", foreground: Color.white, background: LinearGradient(colors: [.red, .orange], startPoint: .bottomLeading, endPoint: .topTrailing)) {
                    Task {
                        await viewModel.login(login: login, password: password)
                    }
                }
                    .disabled(loginButtonDisabled)
                    .padding(.top, 16)
                    .animation(.default, value: loginButtonDisabled)
                HStack(alignment: .center, spacing: 4) {
                    Text("Do not have an account yet?")
                        .foregroundStyle(Color.white)
                    Button("Register.") {
                        authPage = .register
                    }
                    .tint(.orange)
                }
                .font(.callout)
                .frame(maxWidth: .infinity)
            }
        }
        /*
         .onChange(of: viewModel.state.state) { oldValue, newValue in
             if newValue is LoginContractILoginStateSUCCESS {
                 settings.refreshAccount()
             }
         }
         */
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .login)
}
