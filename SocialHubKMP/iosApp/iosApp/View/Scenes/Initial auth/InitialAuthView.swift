//
//  InitialAuthView.swift
//  iosApp
//
//  Created by Jan Janovec on 12.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct InitialAuthView: View {
    @State var username = "the_honzic"
    @State var password = "password"
    @State var currentAuthPage: AuthPage = .login

    var body: some View {
        ZStack {
            // MARK: - Background
            LinearGradient(colors: [.orange, .black, .gray], startPoint: .topLeading, endPoint: .bottomTrailing)
                .ignoresSafeArea()
            // MARK: - Auth view
            Group {
                switch currentAuthPage {
                case .login:
                    InitialLoginView(authPage: $currentAuthPage)
                case .resetPassword:
                    Color.red
                case .register:
                    InitialRegisterView(authPage: $currentAuthPage)
                case .confirmLogin:
                    EmptyView()
                }
            }
            .animation(.smooth, value: currentAuthPage)
                .padding(16)
        }
    }
}

#Preview {
    InitialAuthView()
}

enum AuthPage {
    case login, resetPassword, register, confirmLogin
}
